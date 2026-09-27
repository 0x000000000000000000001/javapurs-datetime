    // Port of Data/Date.js. The JavaScript implementation builds UTC dates
    // (which normalize out-of-range months and days); java.time.LocalDate does
    // the same through the first-of-month base. Years 0-99 keep their value.
    private static java.time.LocalDate __date(int year, int month, int day) {
        return java.time.LocalDate.of(year, 1, 1).plusMonths(month - 1L).plusDays(day - 1L);
    }

    public static Object canonicalDateImpl = (java.util.function.Function<Object, Object>) (ctor) ->
        (java.util.function.Function<Object, Object>) (y) ->
        (java.util.function.Function<Object, Object>) (m) ->
        (java.util.function.Function<Object, Object>) (d) -> {
            java.time.LocalDate date = __date(((Number) y).intValue(), ((Number) m).intValue(), ((Number) d).intValue());
            return ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ctor)
                .apply(date.getYear())).apply(date.getMonthValue())).apply(date.getDayOfMonth());
        };

    public static Object calcWeekday = (java.util.function.Function<Object, Object>) (y) ->
        (java.util.function.Function<Object, Object>) (m) ->
        (java.util.function.Function<Object, Object>) (d) ->
            __date(((Number) y).intValue(), ((Number) m).intValue(), ((Number) d).intValue()).getDayOfWeek().getValue() % 7;

    public static Object calcDiff = (java.util.function.Function<Object, Object>) (y1) ->
        (java.util.function.Function<Object, Object>) (m1) ->
        (java.util.function.Function<Object, Object>) (d1) ->
        (java.util.function.Function<Object, Object>) (y2) ->
        (java.util.function.Function<Object, Object>) (m2) ->
        (java.util.function.Function<Object, Object>) (d2) -> {
            java.time.LocalDate first = __date(((Number) y1).intValue(), ((Number) m1).intValue(), ((Number) d1).intValue());
            java.time.LocalDate second = __date(((Number) y2).intValue(), ((Number) m2).intValue(), ((Number) d2).intValue());
            return (double) (first.toEpochDay() - second.toEpochDay()) * 86400000.0;
        };
