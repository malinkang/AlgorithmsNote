private abstract static class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>,SupportRemove<K, V> {
    Entry<K, V> mExpectedEnd;
    Entry<K, V> mNext;
    ListIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
        this.mExpectedEnd = expectedEnd; //结尾
        this.mNext = start; //开始
    }
    @SuppressWarnings("ReferenceEquality")
    private Entry<K, V> nextNode() {
        if (mNext == mExpectedEnd || mExpectedEnd == null) {
            return null;
        }
        //调用forward方法
        return forward(mNext);
    }
    @Override
    public Map.Entry<K, V> next() {
        Map.Entry<K, V> result = mNext;
        mNext = nextNode();
        return result;
    }   
}
