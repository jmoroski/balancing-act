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

import { ObjectId } from './objectId';
import { TaskFrequency } from 'app/model/api/taskFrequency';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';


export interface BillingRule {
    id?: ObjectId;
    name?: string;
    serviceId?: ObjectId;
    serviceName?: string;
    frequency?: TaskFrequency;
    startDate?: Date;
    endDate?: Date;
    items?: Array<BillingRuleItem>;
}
