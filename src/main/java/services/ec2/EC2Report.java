package services.ec2;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class EC2Report {
	
  public void runReport(File reportDir){
	

	  BufferedWriter writer = null;

	  try{ 
		  
		  final AmazonEC2 ec2 =  AmazonEC2ClientBuilder.defaultClient(); 
		  List <Reservation> reservations =  ec2.describeInstances().getReservations();
		  
		  writer = new BufferedWriter(new FileWriter(reportDir + File.separator + "EC2report_" + System.currentTimeMillis() + ".csv"));
		  writer.write("Platform,State,InstanceId,ImageId,PublicDNSName,TagKey,TagValue,LaunchTime,IAM ARN" + "\n");
	  for(Reservation reservation : reservations ){
		  
		  List <Instance> instances = reservation.getInstances();
		  for (Instance instance : instances){
			  
//			  if (instance.getPlatform() == null){
//				  continue;
//			  }
			  String csvline =  (String) isNull(instance.getPlatform()) + 
					  (String) isNull(instance.getState() != null ? instance.getState().getName() : "")  +
					  (String) isNull(instance.getInstanceId()) +
					  (String) isNull(instance.getImageId()) + 
					  (String) isNull(instance.getPublicDnsName())  +
					  (String) isNull(instance.getTags() != null ? instance.getTags().get(0).getKey() : "") + 
					  (String) isNull(instance.getTags() != null ? instance.getTags().get(0).getValue() : "") +
					  isNull(instance.getLaunchTime()) +
					  (String) isNullLast (instance.getIamInstanceProfile() != null ? instance.getIamInstanceProfile().getArn() : "")  
					  ;
			  
				  
			  writer.write(csvline + "\n");
			  System.out.println(csvline);
		  }
		  
		  writer.flush();
	  }
	  }catch(Exception e){
		  e.printStackTrace();
		 try {
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	  }finally{
		  try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
  }
  
  
  
  public Object isNull(Object obj)
  {
	  return (obj == null ? " " : obj) + ","; 
  }
  
  public Object isNullLast(Object obj)
  {
	  return obj == null ? " " : obj ; 
  }
  
  public static void main(String ...args){
	  
	  if (args.length != 2){
		  System.out.println("Usage: ec2-report -output <Output Directory>");
		  System.exit(1);
	  }else{
		  if (!(new File(args[1])).exists() || !(new File(args[1])).isDirectory() )
		  {
			  System.out.println("Directory :" + args[1] + " either does not exist or is not a directory");
			  System.exit(1);
		  }
	  }
	  
	  
	  new EC2Report().runReport(new File(args[1]));
  }

}
