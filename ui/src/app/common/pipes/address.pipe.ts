import { PipeTransform, Pipe } from "@angular/core";
import { CurrencyPipe } from "@angular/common";
import { ContactInfo } from "app/model/api/contactInfo";

@Pipe({ name: 'baAddress' })
export class AddressPipe implements PipeTransform {
    constructor() { }

    transform(value: ContactInfo): any {
        if (!value) {
            return value;
        }

        var address: string = '';
        if (value.address1 != null) {
            address += `${value.address1}\n`
        }

        if (value.address2 != null) {
            address += `${value.address2}\n`;
        }

        if (value.city != null) {
            address += `${value.city}, ${value.state} ${value.zip}`;
        }

        return address;
    }
}