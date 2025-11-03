<script setup lang="ts">
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

interface Item {
  itemId: number;
  name: string;
  description: string;
}

const router = useRouter();

const pageSize = 10;
const totalPages = ref(1);
const itemList = ref<Array<Item>>([]);
const currentPage = ref(1);
watch(currentPage, (currentPage, previousPage) => {
  if (currentPage !== previousPage) {
    fetchItems(currentPage);
  }
});

const fetchItems = (page: number) => {
  fetch(`${import.meta.env.VITE_API_URL}/items?page=${page}`)
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        totalPages.value = Number(data.data.total);
        itemList.value = data.data.page;
        currentPage.value = page;
      } else {
        ElMessage.error('获取物品列表失败');
      }
    });
};
fetchItems(1);

const modifyItem = (row: Item) => {
  router.push({ name: 'modify', params: { itemId: row.itemId } });
};

const removeItem = (row: Item) => {
  fetch(`${import.meta.env.VITE_API_URL}/item/remove/${row.itemId}`)
    .then(res => res.json())
    .then(data => {
      if (data.success) {
        fetchItems(1);
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
      <el-table-column type="index" width="50" />
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
    <el-pagination layout="prev, pager, next" :page-count="Math.ceil(totalPages / pageSize)"
      v-model:current-page="currentPage" />
  </main>
</template>
