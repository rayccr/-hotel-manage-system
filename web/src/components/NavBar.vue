<template>
    <nav class="navbar bg-primary navbar-expand-lg navbar-dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{name: 'home'}">民宿系统</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :class="route_name == 'home' ? 'nav-link active' : 'nav-link'" :to="{name: 'home'}">Home</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'roomquery' ? 'nav-link active' : 'nav-link'" :to="{name: 'roomquery'}">客房查询</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'roombook' ? 'nav-link active' : 'nav-link'" :to="{name: 'roombook'}">消费记录</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'roomchange' ? 'nav-link active' : 'nav-link'" :to="{name: 'roomchange'}">换房续房</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'roompay' ? 'nav-link active' : 'nav-link'" :to="{name: 'roompay'}">结账退房</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'roomadd' ? 'nav-link active' : 'nav-link'" :to="{name: 'roomadd'}">添加房间</router-link>
                    </li>
                </ul>
                
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            {{$store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <router-link class="dropdown-item" :to="{name: 'userinfo', params: {userId: $store.state.user.id}}">{{ $store.state.user.username }}</router-link>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <router-link class="dropdown-item" :to="{name: 'login'}" @click="logout">退出</router-link>
                        </li>
                        </ul>
                    </li>
                </ul>

                <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'login'}" role="button">
                        登录
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'register'}" role="button">
                        注册
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


</template>

<script>
import { useStore } from 'vuex';
import { computed } from "@vue/reactivity";
import { useRoute } from 'vue-router';

export default{
    name: "NavBar",

    setup(){
        const store = useStore();
        const router = useRoute();
        let route_name = computed(() => router.name);
        
        const logout = () => {
            store.dispatch("logout");
        }

        return {
            route_name,
            logout,
        }
    }


}

</script>

<style scoped>

</style>
