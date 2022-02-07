import { TestBed } from '@angular/core/testing';

import { AccountReplenishmentService } from './account-replenishment.service';

describe('AccountReplenishmentService', () => {
  let service: AccountReplenishmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountReplenishmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
