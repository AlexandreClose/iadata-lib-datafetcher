package fr.toulouse.iadata.apifetcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemProperties 
{
    public String httpProxyHost;
    public String httpProxyPort;
    public String httpsProxyHost;
    public String httpsProxyPort;
    public String httpProxyUser;
    public String httpProxyPassword;

	public String getHttpProxyHost() {
		return httpProxyHost;
	}
	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}
	public String getHttpProxyPort() {
		return httpProxyPort;
	}
	public void setHttpProxyPort(String httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}
	public String getHttpsProxyHost() {
		return httpsProxyHost;
	}
	public void setHttpsProxyHost(String httpsProxyHost) {
		this.httpsProxyHost = httpsProxyHost;
	}
	public String getHttpsProxyPort() {
		return httpsProxyPort;
	}
	public void setHttpsProxyPort(String httpsProxyPort) {
		this.httpsProxyPort = httpsProxyPort;
	}
	public String getHttpProxyUser() {
		return httpProxyUser;
	}
	public void setHttpProxyUser(String httpProxyUser) {
		this.httpProxyUser = httpProxyUser;
	}
	public String getHttpProxyPassword() {
		return httpProxyPassword;
	}
	public void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}
      
}