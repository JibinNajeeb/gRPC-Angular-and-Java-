package org.baeldung.grpc.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.baeldung.grpc.HelloRequest;
import org.baeldung.grpc.HelloResponse;
import org.baeldung.grpc.HelloServiceGrpc.HelloServiceImplBase;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceImplBase {

	private String[] b64;
	private int count = 0;
//  private final String fileName = "ref_volume.ppm";
//  private final String fileName = "/home/quest/POC5/Dicom_Slider_Sample/ref_volume.ppm";
//	private final String fileName = "/home/quest/POC5/Dicom_Slider_Sample/sample.mp4";
//  private final String fileName = "sample.mp4";
	 private final String fileName = "/home/ubuntu/sample.mp4";
  
	public HelloServiceImpl() {
		try {
			changeContrastImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Override
    public void hello(    		
      HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    	if(count > 300)
			count = 0;
        System.out.println("Request received from client:\n" + request);

        String greeting = new StringBuilder()
            .append(b64[count])            
            .toString();

        HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(greeting)
            .build();

        responseObserver.onNext(response);
        
        responseObserver.onCompleted();
        count++;
    }
    
    public String changeContrastImage() throws Exception {
    	FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(fileName);
    	frameGrabber.start();
    	
    	 Frame frame;  
	
			
			
			double frameRate = frameGrabber.getFrameRate();
			System.out.println(
					"Video has " + frameGrabber.getLengthInFrames() + " frames and has frame rate of " + frameRate);
			b64 = new String[frameGrabber.getLengthInFrames()];
			for(int i = 0;i<frameGrabber.getLengthInFrames();i++) {
				System.out.println("Frame "+i);
				frameGrabber.setFrameNumber(i);
				frame = frameGrabber.grab();
				Java2DFrameConverter converter = new Java2DFrameConverter();
				BufferedImage bi = converter.convert(frame);
				
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bi, "jpg", outputStream);
				b64[i] = Base64.getEncoder().encodeToString(outputStream.toByteArray());
				
				
				
			}
//			for(int i =0 ;i < b64.length; i++) {
//				System.out.println("Loop "+i);
//				Thread.sleep(100);				
//				if(i== b64.length - 1)
//					i = 0;
//			}
    	frameGrabber.stop();
        return null;
    }
}
