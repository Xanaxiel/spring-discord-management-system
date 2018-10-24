package pl.bmstefanski.discordms.web.request;

@FunctionalInterface
public interface Requestable<T> {

  T submitRequest();

}
