import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'server'})
export class ServerPipe implements PipeTransform {
    transform(value: string, ...args: any[]): string {
        return value.replace('.jackboxgames.com', '');
    }
}
