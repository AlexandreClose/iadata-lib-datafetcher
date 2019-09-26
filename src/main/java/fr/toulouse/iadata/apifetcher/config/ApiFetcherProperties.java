package fr.toulouse.iadata.apifetcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "apifetcher")
public class ApiFetcherProperties 
{
    public String urlApi;
    public String loginApi;
    public String passwordApi;
    public String securityType;

    public String getUrlApi( )
    {
        return urlApi;
    }
    public void setUrlApi( String urlApi )
    {
        this.urlApi = urlApi;
    }
    public String getLoginApi( )
    {
        return loginApi;
    }
    public void setLoginApi( String loginApi )
    {
        this.loginApi = loginApi;
    }
    public String getPasswordApi( )
    {
        return passwordApi;
    }
    public void setPasswordApi( String passwordApi )
    {
        this.passwordApi = passwordApi;
    }
    public String getSecurityType( )
    {
        return securityType;
    }
    public void setSecurityType( String securityType )
    {
        this.securityType = securityType;
    }
}