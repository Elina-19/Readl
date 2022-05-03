package ru.itis.readl.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import ru.itis.readl.validation.annotations.FieldsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String[] fields;

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(o);
        List<String> fieldValues = new ArrayList<>();

        try {
            for (String fieldName : fields) {
                String fieldValue = (String) wrapper.getPropertyValue(fieldName);
                fieldValues.add(fieldValue);
            }
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Field is not string");
        }catch (BeansException e){
            throw new IllegalArgumentException("Field does not exist");
        }

        return !(fieldValues.size() == fieldValues.stream().distinct().count());
    }
}
