import { PipeTransform, Pipe } from "@angular/core";
import { CurrencyPipe, DatePipe } from "@angular/common";

@Pipe({name: 'baDate'})
export class CustomDatePipe implements PipeTransform {
  constructor() {}

  transform(value: any): any {
    
    
    if (!value) {
      return value;
    }

    console.log(`${value} is ${typeof(value)} converting to ${new Date(value)}`);
    return new DatePipe('en').transform(new Date(value), 'yyyy-MM-dd');
  }
}