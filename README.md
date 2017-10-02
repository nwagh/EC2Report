# EC2Report

This simple tool takes an inventory of EC2 instances and exports to CSV file. This report is useful for inventory purposes.

### Pre-requisites

Ensure that aws credentials are stored on your laptop or PC

#### For Windows
```
 C:\Users\USER_NAME\.aws\credentials
```

#### For Linux or Mac
```
 ~/.aws/credentials 
```

#### Replace following in your credentials files 

```
[default]
aws_access_key_id = <<Your Access Key>>
aws_secret_access_key = <<Your secret access>>
```

#### Ensure Java 8 is installed

### Using command line or powershell prompt execute as below to get the report

### Usage
  #### Step 1: Download target/ec2-report-1.0-SNAPSHOT-jar-with-dependencies.jar to your laptop(https://github.com/nwagh/EC2Report/blob/master/target/ec2-report-1.0-SNAPSHOT-jar-with-dependencies.jar)
  
  #### Step 2: Execute following on command line or powershell
```  
  java -jar <path to ec2-report-1.0-SNAPSHOT-jar-with-dependencies.jar> -output <path to folder for report>
```
