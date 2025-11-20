import { AbstractControl, FormGroup, ValidationErrors } from "@angular/forms";


export function confirmPasswordValidator(passwordFormName: string, matchingPasswordFormName: string) {
    return (formGroup: AbstractControl): ValidationErrors | null => {
        const passwordControl = formGroup.get(passwordFormName);
        const matchingPasswordControl = formGroup.get(matchingPasswordFormName);

        if (!passwordControl || !matchingPasswordControl) {
            return null; // Controls not found, no validation applied
        }

        if (matchingPasswordControl.errors && !matchingPasswordControl.errors['mismatch']) {
            return null; // Another validator has already found an error
        }

        if (passwordControl.value !== matchingPasswordControl.value) {
            matchingPasswordControl.setErrors({ mismatch: true });
            return { mismatch: true };
        } else {
            matchingPasswordControl.setErrors(null); // Clear any previous mismatch error
            return null;
        }
    }
}