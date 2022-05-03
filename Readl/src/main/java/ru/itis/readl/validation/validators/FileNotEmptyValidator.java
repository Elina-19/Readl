package ru.itis.readl.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.readl.validation.annotations.FileNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, Object> {

    private String[] fields;

    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(o);

        try {
            for (String fieldName : fields) {
                MultipartFile fieldValue = (MultipartFile) wrapper.getPropertyValue(fieldName);

                if (fieldValue == null || fieldValue.getSize() == 0){
                    return false;
                }
            }
        }catch (ClassCastException e){
            throw new IllegalArgumentException("Field is not file");
        }catch (BeansException e){
            throw new IllegalArgumentException("Field does not exist");
        }

        return true;
    }
}
