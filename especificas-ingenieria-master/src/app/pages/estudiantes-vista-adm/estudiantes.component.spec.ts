import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EstudiantesComponent } from './estudiantes.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatIconModule } from '@angular/material/icon';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('EstudiantesComponent', () => {
  let component: EstudiantesComponent;
  let fixture: ComponentFixture<EstudiantesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EstudiantesComponent],
      imports: [HttpClientTestingModule, MatIconModule, RouterTestingModule, FormsModule] 
    });
    fixture = TestBed.createComponent(EstudiantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
