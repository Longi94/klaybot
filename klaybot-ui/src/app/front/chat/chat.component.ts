import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../shared/chat.service';

@Component({
    selector: 'app-chat',
    templateUrl: './chat.component.html',
    styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

    isLoading = false;
    message: string;

    constructor(private readonly chatService: ChatService) {
    }

    ngOnInit() {
    }

    sendMessage() {
        this.isLoading = true;
        this.chatService.sendMessage(this.message).subscribe(_ => {
            this.message = '';
            this.isLoading = false;
        });
    }
}
