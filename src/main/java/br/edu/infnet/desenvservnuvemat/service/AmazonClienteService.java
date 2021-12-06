package br.edu.infnet.desenvservnuvemat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class AmazonClienteService {
	
	private AmazonS3 s3Client;

	@Value("${aws.key}")
	private String key;
	
	@Value("${aws.secret}")
	private String secret;
	
	@Value("${aws.bucketName}")
	private String bucket;
	
	@Value("${aws.region}")
	private String region;
	@PostConstruct
	private void initAmazonS3() {
		BasicAWSCredentials basicAwsCredentials = new BasicAWSCredentials(key, secret);
		AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(basicAwsCredentials);
		
		s3Client = AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(awsStaticCredentialsProvider).build();
	}


	
	
	public List<String> listar(){
		List<String> nomeArquivos = new ArrayList<>();
		ObjectListing objectListing = s3Client.listObjects(bucket);
		List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
		if(objectSummaries != null) {
			for(S3ObjectSummary obj : objectSummaries) {
				String nomeArquivo = obj.getKey();
				nomeArquivos.add(nomeArquivo);
			}
		}
	
	return nomeArquivos;
	}
	
	public String save(File file) {
		//String prefixoArquivo = UUID.randomUUID().toString();
		String fileName = file.getName();//prefixoArquivo + "-" + file.getName();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, file);
		s3Client.putObject(putObjectRequest);
		return fileName;
	}
	
	public void delete(String fileName) {
		s3Client.deleteObject(bucket, fileName);	
	}
	
	public File download(String fileName) throws IOException {
		
		S3Object object = s3Client.getObject(bucket, fileName);
		S3ObjectInputStream objectContent = object.getObjectContent();
		byte[] bytes = objectContent.readAllBytes();
		
		File file = File.createTempFile("temp", fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		objectContent.close();
		fos.close();
		return file;
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public String save(MultipartFile multipartFile) {
		try {
			File file = convert(multipartFile);
			return save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
