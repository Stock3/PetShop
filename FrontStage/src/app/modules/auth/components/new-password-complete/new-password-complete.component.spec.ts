import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPasswordCompleteComponent } from './new-password-complete.component';

describe('NewPasswordCompleteComponent', () => {
  let component: NewPasswordCompleteComponent;
  let fixture: ComponentFixture<NewPasswordCompleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPasswordCompleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewPasswordCompleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
