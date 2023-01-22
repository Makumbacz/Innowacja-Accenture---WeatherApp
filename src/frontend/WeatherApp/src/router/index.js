import { createWebHistory, createRouter } from 'vue-router'
import Activities from '../components/Activities.vue'

const routes = [
    { path: '/activities', name: 'activities' ,component: () => import('../components/Activities.vue')}
]
const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;

