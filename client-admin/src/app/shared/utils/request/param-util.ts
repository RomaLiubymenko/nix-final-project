import {HttpParams} from '@angular/common/http';
import {CustomHttpParamEncoder} from "./custom-http-param-encoder";

export interface Pagination {
  page: number;
  size: number;
  sort: string[];
}

export interface Search {
  query?: string;
}

export interface Filter {
  fieldNames?: string[];
  fieldValue?: string[];
}

export interface SearchWithPagination extends Search, Filter, Pagination {
}

export const createRequestOption = (req?: any): HttpParams => {
  let options: HttpParams = new HttpParams({encoder: new CustomHttpParamEncoder()});

  if (req) {
    Object.keys(req).forEach(key => {
      if (!req[key]) {
        return;
      }
      if ((key !== 'sort') && (key !== 'fieldNames') && (key !== 'fieldValue')) {
        if (Array.isArray(req[key])) {
          req[key].forEach((val: string) => {
            options = options.append(key, val);
          });
        } else {
          options = options.set(key, req[key]);
        }
      }
    });

    if (req.sort) {
      req.sort.forEach((val: string) => {
        options = options.append('sort', val);
      });
    }
    if (req.fieldNames && req.fieldValue) {
      for (let i = 0; i < req.fieldNames.length; i++) {
        options = options.append(req.fieldNames[i], req.fieldValue[i]);
      }
    }
  }

  return options;
};
