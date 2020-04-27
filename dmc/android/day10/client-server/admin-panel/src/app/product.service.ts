import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ProductService {

  url = 'http://localhost:4000/product'

  constructor(private httpClient: HttpClient) { }

  postProduct(title: string, description: string, price: number, categoryId: number, thumbnail: any) {
    const body = new FormData()
    body.append('title', title)
    body.append('description', description)
    body.append('price', '' + price)
    body.append('categoryId', '' + categoryId)
    body.append('thumbnail', thumbnail)

    return this.httpClient.post(this.url + '/with-image', body)
  }
}
