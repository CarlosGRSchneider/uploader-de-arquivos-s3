import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.time.LocalDate;

public class S3Uploader {


    public static void main(String[] args) {
//         Utilizar aqui seus valores de chave de acesso, nome do bucket e região da sua conta AWS
        String accessKey = "Sua chave de acesso aqui";
        String secretKey = "sua chave secreta aqui";
        String nomeBucket = "o nome do seu bucket";
        String regiao = " sua região. São Paulo é esta = sa-east-1";

        try {

            BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 s3client = AmazonS3Client.builder()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(regiao)
                    .build();

            String chaveDoArquivo = "massa-de-dados" + LocalDate.now() + ".csv";
            String caminhoDoArquivo = "in/" + chaveDoArquivo;
            File arquivo = new File(caminhoDoArquivo);

            s3client.putObject(new PutObjectRequest(nomeBucket, chaveDoArquivo, arquivo));
            System.out.println("Arquivo Enviado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
