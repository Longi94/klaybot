import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, ValidatorFn } from "@angular/forms";
import { JackboxService } from "../../shared/jackbox.service";
import { MatSnackBar } from "@angular/material";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: 'app-code',
    templateUrl: './code.component.html',
    styleUrls: ['./code.component.scss']
})
export class CodeComponent implements OnInit {

    codeForm = new FormGroup({
        code: new FormControl(null, validateCode)
    });

    constructor(private jackboxService: JackboxService,
                private snackBar: MatSnackBar) {
    }

    ngOnInit() {
    }

    sendCode() {
        this.jackboxService.sendJackboxCode(this.codeForm.value.code).subscribe(() => {
            this.snackBar.open('Code submitted!', null, {duration: 2000});
        }, (error: HttpErrorResponse) => {
            console.log(error);
            switch (error.error) {
                case 1:
                    this.snackBar.open('This code has already been saved.', null, {duration: 2000});
                    break;
                case 2:
                    this.snackBar.open('Unable to reach jackbox.', null, {duration: 2000});
                    break;
                case 3:
                    this.snackBar.open('Jackbox returned error. Most likely invalid code.', null, {duration: 2000});
                    break;
                case 4:
                    this.snackBar.open('Jackbox returned nothing.', null, {duration: 2000});
                    break;
                case 5:
                    this.snackBar.open('Unable to reach twitch.', null, {duration: 2000});
                    break;
                case 6:
                    this.snackBar.open('Twitch returned error', null, {duration: 2000});
                    break;
                case 7:
                    this.snackBar.open('Twitch returned nothing.', null, {duration: 2000});
                    break;
                case 8:
                    this.snackBar.open('Stream is not online', null, {duration: 2000});
                    break;
                default:
                    this.snackBar.open(error.statusText, null, {duration: 2000});
                    break;
            }
        });
    }
}

let codeRegex = /^[A-Z]{4}$/g;

function validateCode(code: FormControl): ValidationErrors {
    let errors = {};

    if (!code.value) {
        errors['required'] = true;
    } else if (!code.value.match(codeRegex)) {
        errors['bad-format'] = true;
    }

    return errors;
}
