import {defineStore} from "pinia";
import router from "../router";

export const useAuthStore = defineStore({
    id: 'auth',
    state: () => {
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user') || '') : null,
            token: localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')|| '') : null,
            returnUrl: '/',
        }
    },
    actions: {
        async login(username, password) {
            const response = await fetch('http://localhost:8080/api/v1/auth/token',
                {
                    method: 'POST',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({username, password})
                });
            if(response.status === 200){
                const token = await response.text();
                localStorage.setItem('user', JSON.stringify(username));
                localStorage.setItem('token', JSON.stringify(token));
                this.token = token;
                this.user = username;
                router.push(this.returnUrl || '/');

            }else{
                throw new Error('Invalid credentials');
            }
        },
       /* async regiser(username, password, email) {
            const response = await fetch('http://localhost:8080/api/v1/auth/token',
                {
                    method: 'POST',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({username, password})
                });
            if(response.status === 200){
                const token = await response.text();
                localStorage.setItem('user', JSON.stringify(username));
                localStorage.setItem('token', JSON.stringify(token));
                this.token = token;
                this.user = username;
                router.push(this.returnUrl || '/');

            }else{
                throw new Error('Invalid credentials');
            }
        },*/
        logout(){
            this.user = null;
            this.token = '';
            localStorage.removeItem('user');
            localStorage.removeItem('token');
            router.push('/login');
        }
    }
})