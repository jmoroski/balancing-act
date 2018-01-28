/**
 * Balancing Act API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * Contact: balancing-act@moroski.info
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

import { BillingRuleItem } from './billingRuleItem';
import { ObjectId } from './objectId';


export interface CalculatedBillingRuleItem extends BillingRuleItem {
    quantity?: number;
    rate?: number;
}
