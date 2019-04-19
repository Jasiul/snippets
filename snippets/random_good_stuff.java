//////////////////////////////////
//String-int -int-String
String a = String.valueOf(2); 
int i = Integer.parseInt(a); 

////////////////////////////////
//String-date
java.util.Date = java.text.DateFormat.getDateInstance().parse(date String);
//
SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yyyy" );
Date date = format.parse( myString );

////////////////////////////////
//date-sql.date
java.util.Date utilDate = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

///////////////////////////
//send http req + fetch
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class Main { 
	public static void main(String[] args) { 
		try { 
			URL my_url= new URL("http://www.somesite.net/sth"); 
			BufferedReader br = newBufferedReader(new InputStreamReader(my_url.openStream())); 
			String strTemp =""; 
			while(null != (strTemp = br.readLine())){
				System.out.println(strTemp); 
				} 
			}catch (Exception ex) { 
				ex.printStackTrace(); } 
	} 
}

//////////////////////////////
//oracle conn
public class OracleJdbcTest
{
    String driverClass = "oracle.jdbc.driver.OracleDriver";
 
    Connection con;
     
    public void init(FileInputStream fs) throws ClassNotFoundException, 
												SQLException, 
												FileNotFoundException, 
												IOException
    {
        Properties props = new Properties();
        props.load(fs);
        String url = props.getProperty("db.url");
        String userName = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        Class.forName(driverClass);
 
        con=DriverManager.getConnection(url, userName, password);
    }
     
    public void fetch() throws SQLException, IOException
    {
        PreparedStatement ps = con.prepareStatement("select SYSDATE from dual");
        ResultSet rs = ps.executeQuery();
         
        while (rs.next())
        {
            // do the thing you do
        }
        rs.close();
        ps.close();
    }
 
    public static void main(String[] args) 
    {
        OracleJdbcTest test = new OracleJdbcTest();
        test.init();
        test.fetch();
    }
}

///////////////////////
//quick copy
public static void fileCopy( File in, File out ) throws IOException
{
	FileChannel inChannel = new FileInputStream( in ).getChannel();
	FileChannel outChannel = new FileOutputStream( out ).getChannel();
	try
	{
		// 64Mb - 32Kb
		int maxCount = (64 * 1024 * 1024) - (32 * 1024);
		long size = inChannel.size();
		long position = 0;
		while ( position < size )
		{
		   position += inChannel.transferTo( position, maxCount, outChannel );
		}
	}
	finally
	{
		if ( inChannel != null )
		{
		   inChannel.close();
		}
		if ( outChannel != null )
		{
			outChannel.close();
		}
	}
}

/////////////////////////////////////
//array-map
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
public class Main {
	public static void main(String[] args) {
		String[][] countries = { 
			{ "United States", "New York" }, 
			{ "United Kingdom", "London" },
			{ "Netherland", "Amsterdam" }, 
			{ "Japan", "Tokyo" }, 
			{ "France", "Paris" } 
		};
		Map countryCapitals = ArrayUtils.toMap(countries);
		System.out.println("Capital of Japan is " + countryCapitals.get("Japan"));
		System.out.println("Capital of France is " + countryCapitals.get("France")); 
	} 
}

////////////////////////////////
//send email
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
 
public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
{
    boolean debug = false;
 
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.example.com");
 
    // add properties
    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(debug);
 
    Message msg = new MimeMessage(session);
 
    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);
 
    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
    for (int i = 0; i < recipients.length; i++)
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);
 
    // custom headers
    msg.addHeader("MyHeaderName", "myHeaderValue");
 
    // subject + Content Type
    msg.setSubject(subject);
    msg.setContent(message, "text/plain");
    Transport.send(msg);
}

//////////////////////////////////
//current method
String methodName = Thread.currentThread().getStackTrace()[1].getMethodName(); 

