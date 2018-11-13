package br.com.michelmilezzi.yapott.converter;

public interface Converter<T extends Object> {

    public T convert(T a);
    
}
