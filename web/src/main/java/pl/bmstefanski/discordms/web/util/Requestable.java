package pl.bmstefanski.discordms.web.util;

@FunctionalInterface
public interface Requestable<T> {

  T submitRequest();

}
