<template>
  <ContentBase>
      <div>醒目留言：啥也没...</div>  
      <h3>功能与注意：</h3>
      <ul>
        <li>
          加油吧
        </li>
      </ul>
</ContentBase>

<ContentBase>
      <div>房间列表</div>  
</ContentBase>

<ContentBase v-for="room in rooms.rooms" :key="room.id">
  <div class="row">
    <div class="col-4" v-if="room.state === false" style="color: green;">
      状态: 可租用
    </div>
    <div class="col-4" v-else style="color: red;">
      状态: 已被租用
    </div>
    <div class="col-4">
      地点：{{ room.location }}
    </div>
    <div class="col-4">
      房型：{{ room.type }}
    </div>
  </div>

  <div class="row">
    <div class="col-4">
      价格：{{ room.price }}
    </div>
    <div class="col-4">
      数量：{{ room.count }}
    </div>
    <div class="col-4">
      折扣：{{ room.discount }}
    </div>
    <div class="col-4">
      房间编号：{{ room.id }}
    </div>
  </div>

  <div>
    介绍：{{ room.info }}
  </div>

</ContentBase>



</template>

<script>
import ContentBase from '../components/ContentBase.vue'
import $ from "jquery"
import { ref } from "vue"
import { useStore } from 'vuex'


export default {
  name: 'HomeView',
  components: {
    ContentBase,
  },

  setup(){
  
    let rooms = ref([]);

    const store = useStore();

    $.ajax({
        url: "http://127.0.0.1:5000/api/user/roomlist/",
        type: "post",
        data: {
        },
        headers: {
            'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
          rooms.value = resp;
        }
      });

    return {
      rooms,
    }
  }


}
</script>

<style scoped>
</style>
