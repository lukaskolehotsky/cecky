package com.example.payload;

public class ItemWithFileResponse {
    private ItemResponse itemResponse;
    private FileResponse fileResponse;

    public ItemWithFileResponse(ItemResponse itemResponse, FileResponse fileResponse) {
        this.itemResponse = itemResponse;
        this.fileResponse = fileResponse;
    }

    public ItemResponse getItemResponse() {
        return itemResponse;
    }

    public void setItemResponse(ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
    }

    public FileResponse getFileResponse() {
        return fileResponse;
    }

    public void setFileResponse(FileResponse fileResponse) {
        this.fileResponse = fileResponse;
    }

    @Override
    public String toString() {
        return "ItemWithFileResponse{" +
                "itemResponse=" + itemResponse.toString() +
                ", fileResponse=" + fileResponse.toString() +
                '}';
    }
}
