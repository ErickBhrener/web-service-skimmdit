package com.fullerton.skimmdit;

import org.skife.jdbi.v2.DBI;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.fullerton.skimmdit.dao.ClientDao;
public class SkimmditAuthenticator implements Authenticator<BasicCredentials, Boolean> {

  private final ClientDao clientDao;

   public SkimmditAuthenticator(DBI jdbi) {
    clientDao = jdbi.onDemand(ClientDao.class);
   }

  public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
    boolean validClient = (clientDao.getUser(c.getUsername(), c.getPassword()) == 1);
    if (validClient) {
      return Optional.of(true);
    }
    return Optional.absent();
  }
}