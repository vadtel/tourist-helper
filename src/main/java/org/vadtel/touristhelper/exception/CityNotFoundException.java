package org.vadtel.touristhelper.exception;

public class CityNotFoundException extends RuntimeException {

    private Long id;

    @Override
    public String getMessage() {
        return String.format("Город с ID=%s не найден", id);
    }

    public CityNotFoundException(Long id) {
        this.id = id;
    }
}
