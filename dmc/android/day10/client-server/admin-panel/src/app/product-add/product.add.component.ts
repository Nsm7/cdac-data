import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-add',
  templateUrl: './product.add.component.html',
  styleUrls: ['./product.add.component.css']
})

export class ProductAddComponent implements OnInit {
  title = ''
  description = ''
  price = 0
  category = 3
  thumbnail = null

  constructor(private service: ProductService) { }

  ngOnInit() { }

  onSelect(event) {
    this.thumbnail = event.target.files[0]
  }

  onSave() {
    this.service
      .postProduct(this.title, this.description, this.price, this.category, this.thumbnail)
      .subscribe(response => {
        console.log(response)
      })
  }

  onCancel() {

  }
}
