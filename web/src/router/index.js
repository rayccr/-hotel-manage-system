import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RoomBookView from '../views/RoomBookView';
import LoginView from '../views/LoginView';
import RegisterView from '../views/RegisterView';
import RoomQueryView from '../views/RoomQueryView';
import NotFoundView from '../views/NotFoundView';
import UserInfo from "../views/UserInfoView";
import RoomChangeView from "../views/RoomChangeView";
import RoomPayView from "../views/RoomPayView";
import RoomAddView from "../views/RoomAddView";
import RoomInfoView from "../views/RoomInfoView"
import store from '@/store';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roomquery/',
    name: 'roomquery',
    component: RoomQueryView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/userinfo/:userId/',
    name: 'userinfo',
    component: UserInfo,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roombook/',
    name: 'roombook',
    component: RoomBookView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roominfo/:roomId/',
    name: 'roominfo',
    component: RoomInfoView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roomchange/',
    name: 'roomchange',
    component: RoomChangeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roompay/',
    name: 'roompay',
    component: RoomPayView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/roomadd/',
    name: 'roomadd',
    component: RoomAddView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/login/',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/register/',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  },
  
  {
    path: '/404/',
    name: '404',
    component: NotFoundView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if(to.meta.requestAuth && !store.state.user.is_login){
    next({name: "login"});
  }else{
    next();
  }
});

export default router
