package ru.sber.school.reflection;

import java.util.Objects;

public class ManyFieldDto {

    @ValidLength(min=5,max=10)
    private String field1;

    private String field2;
    private Integer field3;
    private Boolean field4;

    public ManyFieldDto(String field1, String field2, Integer field3, Boolean field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "ManyField{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3=" + field3 +
                ", field4=" + field4 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManyFieldDto manyField = (ManyFieldDto) o;
        return field1.equals(manyField.field1) &&
                field2.equals(manyField.field2) &&
                field3.equals(manyField.field3) &&
                field4.equals(manyField.field4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3, field4);
    }
}
