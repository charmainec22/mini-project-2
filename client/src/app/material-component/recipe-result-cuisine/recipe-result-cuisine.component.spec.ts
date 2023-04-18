import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeResultCuisineComponent } from './recipe-result-cuisine.component';

describe('RecipeResultCuisineComponent', () => {
  let component: RecipeResultCuisineComponent;
  let fixture: ComponentFixture<RecipeResultCuisineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeResultCuisineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeResultCuisineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
