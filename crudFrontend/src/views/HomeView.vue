<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

interface Item {
  itemId: number;
  name: string;
  description: string;
}

const router = useRouter();

const data_ready = ref(false);
const itemList = ref<Array<Item>>([]);

const fetchItems = () => {
  fetch('http://localhost:8888/items')
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        itemList.value = data.data;
        data_ready.value = true;
      } else {
        ElMessage.error('获取物品列表失败');
      }
    });
};
fetchItems();

const modifyItem = (row: Item) => {
  router.push({ name: 'modify', params: { itemId: row.itemId } });
};

const removeItem = (row: Item) => {
  fetch('http://localhost:8888/item/remove/' + row.itemId)
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        fetchItems();
        ElMessage({
          message: '删除成功',
          type: 'success',
        });
      } else {
        ElMessage.error('删除失败');
      }
    });
};
</script>

<template>
  <main>
    <el-table :data="itemList" stripe style="width: 100%">
      <el-table-column prop="itemId" label="物品ID" width="80" />
      <el-table-column prop="name" label="物品名称" width="120" />
      <el-table-column prop="description" label="物品描述" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="modifyItem(scope.row)">
            修改
          </el-button>
          <el-button link type="primary" size="small" @click="removeItem(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </main>
</template>
