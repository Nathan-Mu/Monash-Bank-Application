package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author nathan
 */
@FacesValidator("TypeValidator")
public class TypeValidator implements Validator{
    private static final String PATTERN = "NotAOption";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!value.toString().isEmpty()) {
            if (value.toString().matches(PATTERN)) {
                FacesMessage msg = new FacesMessage("Please choose an option");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }
    
}
