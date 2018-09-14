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
@FacesValidator("TransNoValidator")
public class TransNoValidator implements Validator{
    private static final String PATTERN = "[0-9]*[1-9][0-9]*";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!value.toString().isEmpty()) {
            if (!value.toString().matches(PATTERN)) {
                FacesMessage msg = new FacesMessage("Transaction No. is a positive integer.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }
    
}
