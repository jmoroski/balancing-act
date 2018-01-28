import { PipeTransform, Pipe } from "@angular/core";
import { CurrencyPipe } from "@angular/common";

@Pipe({
  name: 'baCurrency'
})
export class CustomCurrencyPipe extends CurrencyPipe {
  transform(value: number): any {
    return super.transform(value, 'USD', true, '1.2-2');
  }
}