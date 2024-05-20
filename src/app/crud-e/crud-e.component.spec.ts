import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudEComponent } from './crud-e.component';

describe('CrudEComponent', () => {
  let component: CrudEComponent;
  let fixture: ComponentFixture<CrudEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CrudEComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CrudEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
