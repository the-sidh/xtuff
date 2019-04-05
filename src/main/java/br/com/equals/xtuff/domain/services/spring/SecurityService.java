package br.com.equals.xtuff.domain.services.spring;

public interface SecurityService  {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
