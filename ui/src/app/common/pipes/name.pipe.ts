import { PipeTransform, Pipe } from "@angular/core";
import { CurrencyPipe } from "@angular/common";

@Pipe({name: 'baDisplayName'})
export class NamePipe implements PipeTransform {
  constructor() {}

  transform(value: NamePipe.FullName): any {
    if (!value) {
      return value;
    }

    return `${value.lastName}, ${value.firstName}`;
  }
}

namespace NamePipe {
    export interface FullName {
        firstName: string;
        lastName: string;
    }
}