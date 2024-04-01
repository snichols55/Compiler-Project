
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class JTP {
	int op;
	Scanner in = new Scanner(System.in);
	public JTP() {
		input();
	}
	public void input() {
		Scanner in = new Scanner(System.in);
		int numLBrackets = 0;
		int numRBrackets = 0;
		do {
			System.out.println("Enter 1 for java file or 0 to end");
			int input = in.nextInt();
			op = input;
			if(op == 1) {
				String str;
				Scanner inp = new Scanner(System.in);
				System.out.println("Enter file name followed by .txt");
				String fileName = inp.next();
				 try {
			            File file = new File(fileName);
			            FileReader fileReader = new FileReader(file);
			            BufferedReader bufferedReader = new BufferedReader(fileReader);
			            String line;
			           while ((line = bufferedReader.readLine()) != null) {
			        	        		if(line.contains("{")) {
			        	        			numLBrackets++;
			        	        		}
			        	        		if(line.contains("}")) {
			        	        			numRBrackets++;
			        	        		}
			        	        		correctSyntax(line);
			               // toPython(line, fileName, numLBrackets, numRBrackets);
			           }
			           System.out.println(correctSemantics(fileName));
			                //System.out.println();
			            bufferedReader.close();
				 }catch (IOException e) {
			            System.out.println("An error occurred while reading the file: " + e.getMessage());
			        }
		        }
			else if(op == 0) {
				break;
			}
			else {
				System.out.println("Input must be 1 or 0");
			}
		}while(op != 0);
	}
	public String toPython(String line, String fileName, int numLBrackets, int numRBrackets) throws IOException {
		String python = "";
		if(correctSyntax(line) == true && correctSemantics(fileName) == true && checkBrackets(numLBrackets, numRBrackets) ==true) {
	
		}
		return python;
	}
	public String Expected() {
		return "";
	}
	public boolean correctSyntax(String line) {
		for(int i =0; i < line.length(); i++) {
			if(line.contains("System.out.println(")) {
				String pattern = "System\\.out\\.(println|print)\\(\\s*\"(.*?)\"(\\s*\\+\\s*\\w+)*\\s*\\);";
				String pattern2 = "\\s*System\\.out\\.(println|print)\\(\\s*\\w+\\s*\\)\\s*;";
	        if(line.matches(pattern) == false && line.matches(pattern2) == false) {
	        	return false;
	        }
	        else {
	        	return true;
	        }
		}
	        if(line.matches("for\\s*\\(.*?;.*?;.*?\\)\\s*\\{?")) {
	        	return true;
	        }
	        if(line.matches(".*?class\\s+\\w+\\s*\\{?")) {
	        	return true;
			}
				if(line.matches("\\s*(public|protected|private|static|final|abstract|synchronized|native|strictfp)?\\s*(void|int|double|float|long|short|byte|char|boolean)\\s+\\w+\\s*\\(.*?\\)\\s*\\{?")) {
			return true;
			}
	        if(line.matches("\\s*public\\s+static\\s+void\\s+main\\s*\\(\\s*String\\s*\\[\\s*\\]\\s*args\\s*\\)\\s*\\{?")) {
	        	return true;
			}
			//empty
			if(line.matches("^$")) {
				return true;
			}
			//closing bracket
			if(line.matches("\\}$")) {
				return true;
			}
			//variable
			if(line.matches("\\s*\\w+\\s+\\w+\\s*=\\s*.*?;")) {
				return true;
			}
			//if
			if(line.matches("\\s*if\\s*\\(.*?(<|>|==|&&|\\|\\|).*?\\)\\s*\\{")) {
				return true;
			}
			//array
			if(line.matches("\\s*\\w+\\s*\\[.*?\\]\\s*\\w+\\s*=\\s*new\\s+\\w+\\s*\\[.*?\\]\\s*;")) {
				return true;
			}
			//while
			if(line.matches("\\s*while\\s*\\(.*?\\)\\s*\\{")) {
				return true;
			}
			//math
			if(line.matches("\\s*\\d+\\s*[+\\-*/]\\s*\\d+\\s*")) {
				return true;
			}
			//variable math
			if(line.matches("\\s*\\w+\\s*[+\\-*/]\\s*\\w+\\s*")) {
				return true;
			}
		}
		return false;
	}
/*public boolean correctSemantics(String fileName, String line) {
	String[] lines = fileName.split("\n");
    String type = null;
    
    for (String l : lines) {
        Matcher matcher = Pattern.compile("\\s*(\\w+)\\s+(\\w+)\\s*=.*;").matcher(line);
        if (matcher.find()) {
            String variableType = matcher.group(1);
            String variableName = matcher.group(2);
            
            if (type == null) {
                type = variableType;
            } else if (!type.equals(variableType)) {
                return false; 
            }
        }
    }
    
    return true;
}*/
  // Method to check if variables are of the same type in Java code from a file
  private boolean correctSemantics(String filename) throws IOException {
      String type = null;
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
          String line;
          while ((line = br.readLine()) != null) {
              Matcher matcher = Pattern.compile("\\s*(\\w+)\\s+(\\w+)\\s*=.*;").matcher(line);
              if (matcher.find()) {
                  String variableType = matcher.group(1);
                  String variableName = matcher.group(2);

                  if (type == null) {
                      type = variableType;
                  } else if (!type.equals(variableType)) {
                      return false; // Different types found
                  }
              }
          }
      }
      return true; // All variables are of the same type
  }
public boolean checkBrackets(int numLBrackets, int numRBrackets){
        if(numLBrackets == numRBrackets) {
    		return true;
    	}
	 return false;
}
}
