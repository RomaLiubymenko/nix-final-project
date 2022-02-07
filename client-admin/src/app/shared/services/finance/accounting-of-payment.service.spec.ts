import { TestBed } from '@angular/core/testing';

import { AccountingOfPaymentService } from './accounting-of-payment.service';

describe('AccountingOfPaymentService', () => {
  let service: AccountingOfPaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountingOfPaymentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
