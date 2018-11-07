import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BlackListEditor {
	
	ArrayList<String> blackList;
	
	public BlackListEditor() {
		blackList = new ArrayList<>();
		populateBlackList();
	}
	
	public void deleteHosts() throws Exception {
		String hostLocation = "/etc/hosts";
		java.util.List<String> list = Files.lines(Paths.get(hostLocation)).filter(line -> !blackList.contains(line)).collect(Collectors.toList());
		try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(
                Paths.get(hostLocation)))) {
			list.forEach(pw::println);
		}
	}
	
	public void populateBlackList() {
		blackList.add("0.0.0.0 facebook.com");
		blackList.add("0.0.0.0 youtube.com");
		blackList.add("0.0.0.0 mangadex.org");
		blackList.add("0.0.0.0 twitter.com");
	}
	
	public static void main(String[] args) throws Exception {
		String fileName = "/Users/Oskilla/Desktop/hello";
//		File file = new File(fileName);
//		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		ArrayList<String> blackList = new ArrayList<>();
		blackList.add("facebook.com");
		java.util.List<String> list = Files.lines(Paths.get(fileName)).filter(line -> !blackList.contains(line)).collect(Collectors.toList());
		try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(
                Paths.get(fileName)))) {
			list.forEach(pw::println);
		}
//		bufferedReader.close();
	}
}
