import { Component, OnInit, HostListener } from '@angular/core';
import * as grpcWeb from 'grpc-web';
import {HelloServiceClient} from '../assets/HelloServiceServiceClientPb';
import {HelloRequest, HelloResponse} from '../assets/HelloService_pb';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
 
  helloService: HelloServiceClient;
  request: HelloRequest;
  connection;
  
  ngOnInit(): void {
   this.helloService = new HelloServiceClient('http://localhost:9090', null, null);
 
this.request = new HelloRequest();
this.request.setFirstname('FirstName');
this.request.setLastname('LastName')

  this.helloService.hello(this.request, {},
    (err: grpcWeb.Error, response: HelloResponse) => {
      if (response == null) {
        console.log("Errror = ",err)
      }else {
        console.log(response.getGreeting())
        
      }
    });
    // call.on('status', (status: grpcWeb.Status) => {
    //   // ...
    // });

  }

}

