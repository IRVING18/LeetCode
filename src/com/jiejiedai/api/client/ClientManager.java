package com.jiejiedai.api.client;

import com.jiejiedai.api.client.impl.DefaultJieClient;

/**
 * @author  gaosi
 * @version 20190326
 *
 */
public class ClientManager {

	static String url = "http://www.jiejie888.com:8080/jjd/api";
	static String uid = "9dd5e6a3a5e5401bb2a1bd897f4f453d";
	static String privateKey =  "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC7culAlFH+6vFKQKEuwKhLvIkQXc345vSXCLa31Xn8lc4eL5QcaluklAzYkms8FzwGmAtfEbvCM7l/qN4wRKVpwMQgTrfSMq+/HlKB+E2tdQbMyhBm81A3tjTBWm8ZI95HP2/S0npOGkHt0nnxmEmtRYulODttX320e/eFLm6UOGTVlKfVqCGp/PlgMt/SLLryquNyPm2dsSte4NXC404BTyR2tB8wRki/pn5uU+HbQoLqjftNDytGuO7p99pH9ZUVEXyMAXQZvsOlMQFHmB/uWy6v+tyY8+NYEHNrwTKwj/pPeJPMHHI7wKG8R0RpKrZWYX5Ku001xCH+Z3N3zqEpAgMBAAECggEAC2r8ekqOlBCLkhJNvyNNHQ/m6XBNU5P94hVNy/Tc8V3OqFgNlY2E94ltHuQMVCS2K1CnoKrb3QB5lCUdI6OKz95GFBG3cL5VpjaaoAAX+zDdsSu9xAeeI0aeAAkaK1Xb/EsI7LaVbJx815eyhdzY4A0UbIP4WrkuVwgWE664XZ7W4LblPj7yVU2atZVprULZXYwY6XoYbzsAcRrFdPjN0jjn3H23cHKouOLigOcx1UByEtCSlqo3DGPITo45gcTsV0r5TMh3zKrSiJ36qsyG9LB74DBU3UvBsTHQqaRqgBQp0esNSfPMoPXKgtEsuCdB+wh/uoEiZFHWIO5N2yymYQKBgQD4E1F5jTVzMih96ig3YCG6+8Ti/wi2XTl2HGY8jSY/ciprggd00JBMlxMuK3G7LJ/6ujHuN2AgBX55out3HhgBsphaS50qS01/e+YowbCn4lweOb5L1jsxpRYv+wrp1+8bHsqy92KwmE3+ME4jnHyMqdRbzJ5lNTYvYD/ujxR9OwKBgQDBb87a19ObNq4BKA3ImDrMouzFGDYbNPXu9+JSbcUWD3tKTx9U0PR/x2HYe7hrp9HGDlSFsdlCPPFOppC+e4mPhep5e0bAf8gE1qmLhO4Glw/1OnSsnb5m/irhvl3HRoBt7zZk3uqVud2jgf1DFk0QY4rn0KefUNQTRiSE0HRE6wKBgGylGUOgwk3nI7CxglduNJeNeBbqFsi3X1kI6wWN07hpqYZX9igEx42jhHTt9etBifbm4MMxYVnkzhU7cuBCP8VOkEbLYtOJEzHH827aFSIRksJyC5NvCZGeeW8eBROQzFkWTTPAAGrS6SVvxmj2Od3o6uYhYSV1/nsVLiquYwzNAoGAf5rjaYiRBkZG+WpT1W1e9JnR+Z8kKkSArHkH6vqQ1iG2YmnZsFj7wcNkr6vGF/aoMrBHX77YJJMRWKIWwCG8uFpOOH9zSA/DgjWduPUjFGPY2hwK1chlz4mB/lNXh9rhZCQ2zMolFEp9hJx+1x/CDLqt6ojB2x42GNTfyGG/IasCgYBoW86qs1osmpVFv1cByS2Qoc6UmBVVmaOKW2b300LwlUYkIERIByVM2c+Dk30PtkykvZTGcisXTtSNHMop6LbFJptd8kQ39POA3SJU4JgGQrAB2JNWw57aCCeUQEJ7uALaFDl5YpnY5dscmXbtWoa6DSrwiSU+tew5E1gNTRKX4w==";
	
	static{
//		privateKey += "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ49s96wITVR7lN2";
//		privateKey += "1ZBCIqYKsc72ceqwYrrfVuEdaQe9PH5+FgXwwUlvjYPJaIK678haJRSd4D+Mwa8p";
//		privateKey += "4N00ZDvjKh0Bw4guy4GQTyH0vg3+CewQCzNSaKrOAzO+9URUZH5vMoJvees3XSaf";
//		privateKey += "qqRMQJVPdYD24UrOY6+xlN92hETHAgMBAAECgYB4dL/XZHvi+ttQ7cTka3O0sa9o";
//		privateKey += "vHE7FcoSNrEL7DdnjMXgBFr/aqW4IRK4nzjPSz38ZaKUbxmlRqCplld2C8vAGCte";
//		privateKey += "Zq6lk4LH9KY8vMIh51mLR2NW5QlnznFP57GYUR1bJ0UmAd1OXy7N8NrCnovMjcuy";
//		privateKey += "JRuovrojGCHuA8CVAQJBANC+iwUICDBEdt2q/ozh3U18lfmuu0n//Qw+1x8tx+IC";
//		privateKey += "4skdNspZO6wCNSLJCPizHILF3xEEkrhoYz58R7FQ9g0CQQDCEFGweRjcVFMoTisb";
//		privateKey += "rGmfAVXrGXscHL+yh/EamVf6kAQPqtIYYnMiRte1fQL50jfOXi+F5Nu/begGXV1I";
//		privateKey += "feUjAkBNtGBzwwWpUhMdeSXAs+N5kMMTauP1LAG6qTnTNWuOypw5WVKoCIeaBEhb";
//		privateKey += "ZpKcYbBfTeE3qIgldVZcUE8vxn+RAkEAmMncZwKQrje92QfiLMGLzuJwUM8Y/EMu";
//		privateKey += "MlORYg3FFML6T5OAIi6w0xXPk1Y/V41I3rOM2vAaixbvDMPJQ9/K3QJAOaakUyn/";
//		privateKey += "rNjvDlk+a5gwOyeAFkuYomF6Q9ZBsYIftpeHDZNCEJzROcokBAol3Ltc+s3EGXEk";
//		privateKey += "0argcnyOIWMgGA==";
	}
	
	public static JieClient createClient(){
		return new DefaultJieClient(url,privateKey,uid);
	}
}
