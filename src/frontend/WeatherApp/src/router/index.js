import {createRouter, createWebHashHistory} from 'vue-router';
import {useAuthStore} from "../stores/auth";
// 2. Define some routes
// Each route should map to a component.
// We'll talk about nested routes later.
const routes = [

    { path: '/', name:'Dashboard', component: () => import("@/views/DashboardView.vue")},
    { path: '/login', name:'Login', component: () => import("@/views/LoginView.vue")},
    { path: '/activities', name:'Activities', component: () => import("@/components/Activities.vue")},
    { path: '/forecast', name:'Forecast', component: () => import("@/components/Forecast.vue")},
    { path: '/register', name:'Registration', component: () => import("@/views/RegisterView.vue")},

]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

router.beforeEach(async  (to) =>{
    const publicPages = ['/login', '/register']
    const authRequired = !publicPages.includes(to.path)
    const auth = useAuthStore()

    //if auth is required and the user is not logged in
    if(authRequired && !auth.user){

        return {name: 'Login'};
    }
})

export default router;