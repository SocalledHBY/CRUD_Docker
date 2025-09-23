<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <el-container>
    <el-header>CRUD Docker</el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu>
          <RouterLink to="/">
            <el-menu-item index="1">
              物品列表
            </el-menu-item>
          </RouterLink>
          <RouterLink to="/add">
            <el-menu-item index="2">
              添加物品
            </el-menu-item>
          </RouterLink>
        </el-menu>
      </el-aside>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.el-container {
  height: 100%;
}

.el-header {
  display: flex;
  align-items: center;
  font-size: large;
  background-color: var(--el-color-primary-light-7);
}

.el-aside {
  background-color: var(--el-color-primary-light-9);
}

.el-menu-item {
  font-size: medium;
}
</style>
