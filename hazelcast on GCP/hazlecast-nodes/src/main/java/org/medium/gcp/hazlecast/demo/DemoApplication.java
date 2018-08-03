package org.medium.gcp.hazlecast.demo;

		import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;

        import java.awt.event.InvocationEvent;
        import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.io.OutputStream;
		import java.lang.reflect.Array;
        import java.lang.reflect.InvocationTargetException;
        import java.net.HttpURLConnection;
		import java.net.MalformedURLException;
		import java.net.ProtocolException;
		import java.net.URL;
        import java.util.*;
        import java.util.logging.Level;
		import java.util.logging.Logger;
		import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		System.out.println(fetchChatLineStats(Arrays.asList("V_THO_PK_TR_Auftrag_100","V_TMD_PK_TS_Platin_100")));
	}

	public static Map<String, Integer> fetchChatLineStats(List<String> chatLines) {
        HttpURLConnection conn = null;
		try {
			URL url = new URL("http://infcxmun100.int.infinit-services.com:6500/genesys/1/service/getChatLineAvailAgents");
            conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
			String dupa = chatLines.stream().map(
                    (t) -> {return "\"" + t + "\"";}
            ).collect(Collectors.joining(","));

			String str1 =  "_parameter={\"chatLineList\":[REPLACER]}".replace("REPLACER", dupa);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            chatLineList l = new chatLineList();
            l.chatLineList.addAll(chatLines);

			try (OutputStream os = conn.getOutputStream()) {
				os.write( ("_parameter="+ gson.toJson(l)).getBytes("UTF-8") );
			}

            ChatLineListResponce dto = new Gson().fromJson(new InputStreamReader(conn.getInputStream()), ChatLineListResponce.class);

            System.out.println(dto);
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String output;
//			StringBuilder sb = new StringBuilder();
//
//			while ((output = br.readLine()) != null) {
//				sb.append(output);
//			}
//
//            String response = sb.toString();
//
//			if(response.contains("\"returnCode\":1")){
//                final String replace = response
//                                    .replace("{\"status\":\"ok\",\"_result\":{\"chatLineStats\":{", "")
//                                    .replace("},\"returnCode\":1}}", "");
//
//                StringTokenizer st = new StringTokenizer(replace, ",");
//                Map<String, Integer> returnMap = new HashMap<>();
//                while(st.hasMoreElements()){
//                    final String entery = st.nextToken();
//                    final String[] split = entery.split(":");
//                    returnMap.put(split[0], Integer.valueOf(split[1]));
//                }
//
//
//                return returnMap;
//            } else {
//			    throw new RuntimeException("");
//            }

		} catch (IOException  ex) {
			Logger.getLogger(DemoApplication.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("");
		} finally {
		    if(conn != null){
                conn.disconnect();
            }
        }
        return null;
	}

}
